public class Planet{
    
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /**constructor */
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
                      this.xxPos = xP;
                      this.yyPos = yP;
                      this.xxVel = xV;
                      this.yyVel = yV;
                      this.mass = m;
                      this.imgFileName = img;
                  }

    /**copy constructor */
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    /**calculate distance between two Planets */
    public double calcDistance(Planet p){
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**Calculate force exerted on this planet by the given planet. */
    public double calcForceExertedBy(Planet p){
        double G = 6.67E-11;
        return (G * this.mass * p.mass) / (this.calcDistance(p) * this.calcDistance(p));
    }

    /**Calculate force exerted in the X direction */
    public double calcForceExertedByX(Planet p){
        double F = calcForceExertedBy(p);
        double r = calcDistance(p);
        double dx = p.xxPos - this.xxPos;
        return F * (dx / r);
    }

    /**Calculate force exerted in the Y direction */
    public double calcForceExertedByY(Planet p){
        double F = calcForceExertedBy(p);
        double r = calcDistance(p);
        double dy = p.yyPos - this.yyPos;
        return F * (dy / r);
    }

    public double calcNetForceExertedByX(Planet[] Planets){
        double result = 0.0;
        for(Planet each:Planets){
            if (each.equals(this)){
                continue;
            }
            result += calcForceExertedByX(each);
        }
        return result;
    }

    public double calcNetForceExertedByY(Planet[] Planets){
        double result = 0.0;
        for(Planet each:Planets){
            if (each.equals(this)){
                continue;
            }
            result += calcForceExertedByY(each);
        }
        return result;
    }

    public void update(double dt, double fX, double fY){
        double ax, ay;
        ax = fX / this.mass;
        ay = fY / this.mass;
        this.xxVel = this.xxVel + dt * ax;
        this.yyVel = this.yyVel + dt * ay;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;     
    }    

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
        StdDraw.show();
    }
}               