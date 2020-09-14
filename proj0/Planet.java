public class Planet {
    public double xxPos;   // Its current x position
    public double yyPos;   // Its current y position
    public double xxVel;   // Its current velocity in the x direction
    public double yyVel;   // Its current velocity in the y direction
    public double mass;    // Its mass
    public String imgFileName; //The name of the file that corresponds to the image that depicts the body
    private static double G = 6.67E-11;   //gravitational constant
    /** First constructer */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /** Sencond constructer
     *  take in a Body object and initialize an identical Body object
     */
    public Planet(Planet b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /** Return the distance between two Bodys */
    public double calcDistance(Planet b) {
        return Math.sqrt(Math.pow(b.xxPos - this.xxPos, 2) + Math.pow(b.yyPos - this.yyPos, 2));
    }

    /** Return a double describing the force exerted on this body by the given body. */
    public double calcForceExertedBy(Planet b) {
        return (G * b.mass * this.mass) / Math.pow(this.calcDistance(b), 2);
    }

    /** Return the force exerted in the X direction */
    public double calcForceExertedByX(Planet b) {
        return this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
    }

    /** Return the force exerted in the Y direction */
    public double calcForceExertedByY(Planet b) {
        return this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
    }

    /** Return the net X force exerted by all bodies upon the current Body. */
    public double calcNetForceExertedByX(Planet[] allBodys) {
        double netForce = 0;
        for (Planet b : allBodys) {
            if (!b.equals(this)) {
                netForce += this.calcForceExertedByX(b);
            }
        }
        return netForce;
    }

    /** Return the net Y force exerted by all bodies upon the current Body. */
    public double calcNetForceExertedByY(Planet[] allBodys) {
        double netForce = 0;
        for (Planet b : allBodys) {
            if (!b.equals(this)) {
                netForce += this.calcForceExertedByY(b);
            }
        }
        return netForce;
    }

    /** Update the postion and velcity */
    public void update(double dt, double fX, double fY) {
        double xA = fX / mass;
        double yA = fY / mass;
        xxVel += xA * dt;
        yyVel += yA * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}