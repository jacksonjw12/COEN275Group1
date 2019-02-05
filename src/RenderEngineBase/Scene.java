package RenderEngineBase;

import java.util.ArrayList;

public class Scene {

    private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    private Camera activeCamera;
    private ArrayList<GUIElement> guiElements = new ArrayList<GUIElement>();
    private String name = "Unnamed Scene";

    public Scene(){
        this.activeCamera = new Camera(new Vector3(-1,0,-1));
    }
    public Scene(String name){
        this.activeCamera = new Camera(new Vector3(-1,0,-1));
        this.name = name;
    }

    public void update(Time time,Input input,Application application){


    }
    public String getName(){return this.name;}
    public void setName(String name){this.name=name;}


    public void addGameObject(GameObject object) {
        gameObjects.add(object);
    }

    public void removeGameObject(GameObject object) {
        gameObjects.remove(gameObjects.indexOf(object));
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void clearScene() {
        gameObjects.clear();
    }

    public void setActiveCamera(Camera camera) {
        activeCamera = camera;
    }

    public Camera getActiveCamera() {
        return activeCamera;
    }


}
