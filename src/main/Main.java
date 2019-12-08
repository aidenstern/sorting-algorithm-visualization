package main;

import screens.View;

public class Main {

    public static void main(String[] args) {
	    new Main().run();
    }

    public void run() {
        View view = new View();
        view.start();
    }
}
