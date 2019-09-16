package io.vertx.book.message;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.eventbus.Message;
import io.vertx.rxjava.ext.web.Router;

public class HttpServerMicroservice extends AbstractVerticle {

    @Override
    public void start() {
        Router router = Router.router(vertx);

        router.route(HttpMethod.GET, "/products")
            .handler(req -> {
                vertx.eventBus()
                    .<JsonArray>rxSend("products", null)
                    .map(Message::body)
                    .subscribe(
                        x -> req.response()
                            .putHeader("content-type", "application/json")
                            .end(x.encodePrettily()),
                        t -> req.response()
                            .setStatusCode(500)
                            .end(t.getMessage())
                    );
            });

        vertx.createHttpServer().requestHandler(router).listen(8080);
        }
    }
}
