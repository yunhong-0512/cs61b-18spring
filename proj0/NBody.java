public class NBody {

    /** Return the radius of the universe in the given file */
    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readDouble();
        double radius = in.readDouble();
        return radius;
    }

    /** Return an array of Bodys corresponding to the bodies in the given file */
    public static Planet[] readPlanets (String filename) {
        In in = new In(filename);
        int N = in.readInt();
        in.readDouble();
        Planet[] universe = new Planet[N];
        for (int i = 0; i < N; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            universe[i] = new Planet(xP, yP, xV, yV, m, img);
        }
        return universe;
    }

    public static void main(String[] args) {
        // collect all needed input
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] bodies = readPlanets(filename);

        //draw the background
        String imageToDraw = "images/starfield.jpg";
        StdDraw.enableDoubleBuffering();
        int scale = 100; //Sets up the universe
        StdDraw.setScale(-1 * scale, scale);
        StdDraw.clear();

        // Anination
        double time = 0;
        while (time < T) {

            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            // Calculate the net x and y forces for each Body
            for (int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            // Draw the background image
            StdDraw.picture(0, 0, imageToDraw);
            // Draw all of the Bodies
            for (int i = 0; i < bodies.length; i++) {
                // update each body’s position, velocity, and acceleration
                bodies[i].update(dt, xForces[i], yForces[i]);
                StdDraw.picture(bodies[i].xxPos / radius * scale, bodies[i].yyPos / radius * scale, "images/" + bodies[i].imgFileName);
            }
            // Show the offscreen buffer
            StdDraw.show();
            // Pause the animation for 10 milliseconds
            StdDraw.pause(10);
            //Increase time variable
            time += dt;
        }

        // print out the final state of the universe
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }

    }


}
