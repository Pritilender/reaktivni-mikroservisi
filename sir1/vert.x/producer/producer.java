package io.vertx.book.message;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;

public class ProductsMicroservice extends AbstractVerticle {

    @Override
    public void start() {
        vertx.eventBus().<String>consumer("products", message -> {
            ArrayList<JsonObject> products = new ArrayList<JsonObject>();
            products.add(productFactory("Apples", 5));
            products.add(productFactory("Oranges", 10));
            products.add(productFactory("Bananas", 6));

            JsonArray json = new JsonArray(products);
            message.reply(json);
        });
    }

    private JsonObject productFactory(String name, Integer price) {
      return new JsonObject()
        .put("name", name)
        .put("price", price);
    }
}
