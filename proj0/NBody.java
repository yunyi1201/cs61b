import java.sql.Time;

import javax.imageio.event.IIOReadProgressListener;
import javax.swing.plaf.metal.MetalBorders.PaletteBorder;

public class NBody {

    public static double readRadius(String file) {
        In in = new In(file);
        in.readInt();
        double R = in.readDouble(); 
        return R;
    }   

    public static Planet[] readPlanets(String file){
        Planet[] ansPlanet = new Planet[100];
        In in = new In(file);
        in.readInt();
        in.readDouble();
        int cnt = 0;
        while(true) {
            try {
                double xxPos = in.readDouble();
                double yyPos = in.readDouble();
                double xxVel = in.readDouble();
                double yyVel = in.readDouble();
                double mass  = in.readDouble();
                String img   = in.readString();
                ansPlanet[cnt] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
                cnt ++;
            } catch( Exception e) {
                break;
            }
        }
        Planet[] returnPlanets = new Planet[cnt];
        for(int i = 0; i < cnt; i++) {
            returnPlanets[i] = ansPlanet[i];
        }
        return returnPlanets;
    }
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("you should input three arguments");
            System.out.println("e.g. 1.0 2.3 ./data/planets");
            return ;
        }
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String path = args[2];
        double eps  = 0.01;
        double R = readRadius(path);
        Planet[] planets = readPlanets(path);
       
        StdDraw.setScale(-1*R, R);
        StdDraw.picture(0, 0, "./images/starfield.jpg");

        for(Planet p : planets) {
            p.draw();
        }
        

        // StdDraw.enableDoubleBuffering()
        int len = planets.length;
        double[] XForce = new double[len];
        double[] YForce = new double[len];

        double startTime = 0.0;
        // loop update position ... 

        StdDraw.enableDoubleBuffering();
        while(Math.abs(startTime - T) > eps * Math.max(startTime, T)) {
            for(int i = 0; i < len; i++) {
                XForce[i] = planets[i].calcNetForceExertedByX(planets); 
                YForce[i] = planets[i].calcNetForceExertedByY(planets); 
            }    

            for(int i = 0; i < len; i++) {
                planets[i].update(dt, XForce[i], YForce[i]);
            }

            StdDraw.picture(0, 0, "./images/starfield.jpg");

            for(Planet p : planets) {
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            startTime += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", R);
        for (int i = 0; i < planets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}