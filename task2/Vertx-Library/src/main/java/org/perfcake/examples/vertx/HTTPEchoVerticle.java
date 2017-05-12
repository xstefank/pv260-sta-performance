/*
 * -----------------------------------------------------------------------\
 * PerfCake
 *  
 * Copyright (C) 2010 - 2013 the original author or authors.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * -----------------------------------------------------------------------/
 */
package org.perfcake.examples.vertx;

import entities.Loan;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.platform.Verticle;

/**
 * @author Stanislav Chren
 */
public class HTTPEchoVerticle extends Verticle {

    private abstract static class RequestThread implements Runnable {

        protected final HttpServerRequest req;
        protected final Buffer body;

        public RequestThread(final HttpServerRequest req, final Buffer body) {
            this.req = req;
            this.body = body;
        }
    }

    // Max 100 simultaneous requests
    private static class GetThread extends RequestThread {

        public GetThread(HttpServerRequest req, Buffer body) {
            super(req, body);
        }

        @Override
        public void run() {
            DatabaseConnector connector = new DatabaseConnector();
            req.response().end("Done GET.");
            connector.closeConnector();
        }

    }

//    public void start() {
//        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {
//            public void handle(final HttpServerRequest req) {
//                req.response().setStatusCode(200);
//                req.response().headers().set("Content-Type", "text/html; charset=UTF-8");
//                req.bodyHandler(new Handler<Buffer>() {
//                    public void handle(Buffer body) {
//                        RequestThread thread = null;
//
//                        if ("GET".equals(req.method().toUpperCase())) {
//                            thread = new GetThread(req, body);
//                        }
//
//                        if (thread != null) {
//                            new Thread(thread).start();
//                        }
//                    }
//                });
//            }
//        }).listen(8080);
//    }
    public void start() {
        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest req) {
                System.out.println("Got request: " + req.uri());
                System.out.println("Headers are: ");
                for (Map.Entry<String, String> entry : req.headers()) {
                    System.out.println(entry.getKey() + ":" + entry.getValue());
                }
                List<Entry<String, String>> entries = req.params().entries();
                List<Loan> loans = new LinkedList<>();
                for (Entry<String, String> entry : entries) {
                    System.out.println(entry.getKey() + " = " + entry.getValue());
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                    Date bookDate = null;
                    try {
                        bookDate = formatter.parse(entry.getValue());
                    } catch (ParseException ex) {
                        Logger.getLogger(HTTPEchoVerticle.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    

                }
                DatabaseConnector connector = new DatabaseConnector();
                loans = connector.getRandomUnreturnedBooks();
                connector.closeConnector();

                req.response().headers().set("Content-Type", "text/html; charset=UTF-8");
                req.response().end("<html><body>" + loans.toString() + "</body></html>");
            }
        }).listen(8080);
    }
}
