public class Planet {
    static final double G = 6.67e-11;
    static final String prefixPath = "./images/";
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass  = m;
        this.imgFileName = img;
    }    
    public Planet(Planet p) {
        this.xxPos = p.xxPos; 
        this.yyPos = p.yyPos; 
        this.xxVel = p.xxVel; 
        this.yyVel = p.yyVel; 
        this.mass  = p.mass; 
        this.imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p) {
        double dx = (this.xxPos - p.xxPos) * (this.xxPos - p.xxPos);
        double dy = (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos);
        return Math.sqrt(dx + dy);
    }
    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        return G * (this.mass * p.mass) / (r * r);
    }
    public double calcForceExertedByX(Planet p) {
        double r = this.calcDistance(p);
        double F = this.calcForceExertedBy(p);
        double dx = p.xxPos - this.xxPos;
        return F * dx / r; 
    }
    public double calcForceExertedByY(Planet p) {
        double r = this.calcDistance(p);
        double F = this.calcForceExertedBy(p);
        double dy = p.yyPos - this.yyPos;
        return F * dy / r; 
    }

    public double calcNetForceExertedByX(Planet[] p) {
        double netX = 0;
        for(int i = 0; i < p.length; i ++) {
            if(this.equals(p[i])) continue;
            netX += this.calcForceExertedByX(p[i]);
        }
        return netX;
    }

    public double calcNetForceExertedByY(Planet[] p) {
        double netY = 0;
        for(int i = 0; i < p.length; i ++) {
            if(this.equals(p[i])) continue;
            netY += this.calcForceExertedByY(p[i]);
        }
        return netY;
    }
    
    public void update(double dt, double Fx, double Fy) {
        double ax, ay;
        ax = Fx / this.mass;
        ay = Fy / this.mass;
        this.xxVel += dt * ax;
        this.yyVel += dt * ay;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, prefixPath+this.imgFileName);
    }
}
