package org.example.bookshelf;

import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;

public class BookshelfApp extends NanoHTTPD {

    RequestURLMapper requestURLMapper= new RequestURLMapper();

    public BookshelfApp(int port) throws IOException {
        super(port);
        start(5000, false);
        System.out.println("Server has been started");
    }

    public static void main(String[] args) {
        try {
            new BookshelfApp(8080);
        } catch (IOException e) {
            System.out.println("Server didn't start because of \n" + e);
        }
    }

    @Override
    public Response serve(IHTTPSession session) {
        return requestURLMapper.delegateRequest(session);
    }

}
