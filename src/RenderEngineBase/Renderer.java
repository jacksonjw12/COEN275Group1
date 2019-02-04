package RenderEngineBase;

import java.awt.*;
import java.awt.image.VolatileImage;
import java.util.*;

public class Renderer {
    private Application application;
    private Camera camera;
    ArrayList<String> debugLineColor = new ArrayList<String>();

    int targetFPS = 60;
    double maxDelay=1.0/targetFPS;




    public Renderer(Application application){
        this.application = application;
    }

    public void render(Scene scene,Time time){

        Graphics g = this.application.getStrategy().getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, application.getWidth(), application.getHeight());

        //System.out.println("frame");
        drawCameraView(scene,g);

//        g.setColor(Color.ORANGE);
//        g.drawString(Double.toString(1/time.getDTAverage()),10,10);
//
//        g.fillRect((int)scene.lineX, (int)scene.lineY, 100, 100); // arbitrary rendering logic



        g.dispose();
        this.application.getStrategy().show();
    }

    public void drawCameraView(Scene scene, Graphics context) {
        // Get the camera
       camera = scene.getActiveCamera();
        if (camera == null) {
            return;
        }

        // Collect the models to render
        ArrayList<GameObject> objects = scene.getGameObjects();
        ArrayList<Model> allModels = new ArrayList<Model>();
        for (GameObject object : objects) {
            if (object instanceof Model && ((Model) object).isVisible()) {
                allModels.add((Model) object);
            }
        }

        // Order models by paint order
        Collections.sort(allModels, new Comparator<Model>() {
            public int compare(Model m1, Model m2) {
                if (m1.getPaintOrder() > m2.getPaintOrder()) {
                    return 1;
                } else if (m1.getPaintOrder() < m2.getPaintOrder()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        int currentModelIndex = 0;
        while (currentModelIndex < allModels.size()) {
            int currentPaintOrder = allModels.get(currentModelIndex).getPaintOrder();

            // Fill the list of models with the same paint order
            ArrayList<Model> models = new ArrayList<Model>();
            while (currentModelIndex < allModels.size() && allModels.get(currentModelIndex).getPaintOrder() == currentPaintOrder) {
                models.add(allModels.get(currentModelIndex));
                currentModelIndex++;
            }

            // All the polygons in all the models
            int totalFaces = 0;
            for (Model model : models) {
                totalFaces += model.getFaces().length;
            }
            ArrayList<Polygon2D> polygons = new ArrayList<Polygon2D>(totalFaces);
            int polygonIndex = 0;

            // Add every face in every model to the list of polygons
            for (Model model : models) {
                // Faces
                Face[] faces = model.getFaces();

                // Vertices
                Vector3[] vertices = model.getVertices();
                Vector3[] projectedVertices = new Vector3[vertices.length];

                // PicassoEngine.Face centers
                Vector3[] centroids = model.getFaceCenters();
                double[] centroidDepths = new double[centroids.length];

                // Project vertices and put them in a corresponding screen space array
                for (int vertex = 0; vertex < vertices.length; vertex++) {
                    projectedVertices[vertex] = project(vertices[vertex]);
                }

                // Add all the vertices in all the faces in this model to a polygons ArrayList
                for (int face = 0; face < faces.length; face++) {
                    boolean onScreen = false;
                    int[] vertexIndexes = faces[face].getVertexIndexes();
                    Vector2[] faceVertices = new Vector2[vertexIndexes.length];

                    for (int i = 0; i < vertexIndexes.length; i++) {
                        // Add the projected vertex as a screen point in the 2D screen face
                        faceVertices[i] = new Vector2(projectedVertices[vertexIndexes[i]].x, projectedVertices[vertexIndexes[i]].y);

                        // Check if it's on screen
                        if (faceVertices[i].x > 0 && faceVertices[i].x < application.getWidth() && faceVertices[i].y > 0 && faceVertices[i].y < application.getHeight() && projectedVertices[vertexIndexes[i]].z > 0) {
                            onScreen = true;
                        }
                    }

                    if (onScreen) {
                        // Add this face as a polygon in the final array
                        Vector3 v1 = vertices[vertexIndexes[0]].getDifference(vertices[vertexIndexes[1]]);
                        Vector3 v2 = vertices[vertexIndexes[2]].getDifference(vertices[vertexIndexes[1]]);
                        double brightness = v1.getCrossProduct(v2).getAngleDifference(new Vector3(5, 10, 0)) / Math.PI;
                        polygons.add(new Polygon2D(faceVertices, project(centroids[face]), faces[face].getColor(), brightness));
                    }
                }
            }

            Collections.sort(polygons, new Comparator<Polygon2D>() {
                public int compare(Polygon2D p1, Polygon2D p2) {
                    if (p1.getProjectedCentroid().z > p2.getProjectedCentroid().z) {
                        return -1;
                    } else if (p1.getProjectedCentroid().z < p2.getProjectedCentroid().z) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });

            // Draw each polygon
            for (Polygon2D polygon : polygons) {
                int[] x = new int[polygon.getPoints().length];
                int[] y = new int[x.length];

                for (int point = 0; point < x.length; point++) {
                    x[point] = Math.round((float) polygon.getPoints()[point].x);
                    y[point] = Math.round((float) polygon.getPoints()[point].y);
                }

                context.setColor(Color.decode("#" + polygon.getColor()));
                context.fillPolygon(x, y, x.length);
//		    	context.setColor(Color.black); // Set color to black for the below options
//		    	context.drawPolygon(x, y, x.length); // Draw wireframe outline
//		    	context.drawOval((int) polygon.getProjectedCentroid().x, (int) polygon.getProjectedCentroid().y, 10, 10); // Draw centroid used in z-sorting
            }
        }

        // Draw debug lines
//        for (int i = 0; i < debugLineStart.size(); i++) {
//            Vector3 start = project(debugLineStart.get(i));
//            Vector3 end = project(debugLineEnd.get(i));
//            context.setColor(Color.decode("#" + debugLineColor.get(i)));
//            context.drawLine((int) start.x, (int) start.y, (int) end.x, (int) end.y);
//        }
    }



    private Vector3 project(Vector3 point) {
        Vector3 point3d = new Vector3(point.x, point.y, point.z);

        Vector3 screenPoint = new Vector3();

        screenPoint.x = Math.cos(camera.getRotation().getEulerAngles().y) * (Math.sin(camera.getRotation().getEulerAngles().z) * (point3d.y - camera.getPosition().y) + Math.cos(camera.getRotation().getEulerAngles().z) * (point3d.x - camera.getPosition().x)) - Math.sin(camera.getRotation().getEulerAngles().y) * (point3d.z - camera.getPosition().z) + 0.5;
        screenPoint.y = Math.sin(camera.getRotation().getEulerAngles().x) * (Math.cos(camera.getRotation().getEulerAngles().y) * (point3d.z - camera.getPosition().z) + Math.sin(camera.getRotation().getEulerAngles().y) * (Math.sin(camera.getRotation().getEulerAngles().z) * (point3d.y - camera.getPosition().y) +
                Math.cos(camera.getRotation().getEulerAngles().z) * (point3d.x - camera.getPosition().x))) + Math.cos(camera.getRotation().getEulerAngles().x) * (Math.cos(camera.getRotation().getEulerAngles().z) * (point3d.y - camera.getPosition().y) - Math.sin(camera.getRotation().getEulerAngles().z) * (point3d.x - camera.getPosition().x)) + 0.5;
        screenPoint.z = Math.cos(camera.getRotation().getEulerAngles().x) * (Math.cos(camera.getRotation().getEulerAngles().y) * (point3d.z - camera.getPosition().z) + Math.sin(camera.getRotation().getEulerAngles().y) * (Math.sin(camera.getRotation().getEulerAngles().z) * (point3d.y - camera.getPosition().y) +
                Math.cos(camera.getRotation().getEulerAngles().z) * (point3d.x - camera.getPosition().x))) - Math.sin(camera.getRotation().getEulerAngles().x) * (Math.cos(camera.getRotation().getEulerAngles().z) * (point3d.y - camera.getPosition().y) - Math.sin(camera.getRotation().getEulerAngles().z) * (point3d.x - camera.getPosition().x));

        Vector3 newPoint = new Vector3();

        double aspectRatio = (double) application.getWidth() /application.getHeight();
        double ez = 1 / Math.tan(camera.getFov() / 2);
        double x = (screenPoint.x - .5) * (ez / screenPoint.z) / aspectRatio;
        double y = -(screenPoint.y - .5) * (ez / screenPoint.z);

        newPoint.x = (int) (x * application.getWidth() + application.getWidth() / 2);
        newPoint.y = (int) (y * application.getHeight() + application.getHeight() / 2);
        newPoint.z = screenPoint.z;

        return newPoint;
    }


}