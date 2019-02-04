package RenderEngineBase;

public class GameObject {
    private String name;
    private Vector3 position;
    private Quaternion rotation;
    private Vector3 scale;
    private Vector3 velocity;
    private Vector3 rotationalVelocity;


    public GameObject(String name) {
        this(name, new Vector3(), new Vector3(), new Vector3(1, 1, 1));
    }

    public GameObject(String name, Vector3 position) {
        this(name, position, new Vector3(), new Vector3(1, 1, 1));
    }

    public GameObject(String name, Vector3 position, Vector3 rotation) {
        this(name, position, rotation, new Vector3(1, 1, 1));
    }

    public GameObject(String name, Vector3 position, Vector3 rotation, Vector3 scale) {
        this.name = name;
        this.position = position;
        this.rotation = new Quaternion(rotation);
        this.scale = scale;
        this.velocity = new Vector3();
        this.rotationalVelocity = new Vector3();

    }
    public Vector3 getVelocity(){
        return this.velocity;
    }
    public void setVelocity(Vector3 velocity){
        this.velocity = velocity;
    }
    public Vector3 getRotationalVelocity(){
        return this.velocity;
    }
    public void setRotationalVelocity(Vector3 rotationalVelocity){
        this.rotationalVelocity = rotationalVelocity;
    }



    public String getName() {
        return name;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public void addPosition(Vector3 deltaPosition) {
        setPosition(getPosition().getSum(deltaPosition));
    }

    public Quaternion getRotation() {
        return rotation;
    }

    public void setRotation(Vector3 rotation) {
        this.rotation = new Quaternion(rotation);
    }

    public void setRotation(Quaternion rotation) {
        this.rotation = rotation;
    }

    public void addRotation(Vector3 deltaRotation) {
        setRotation(getRotation().getProduct(new Quaternion(deltaRotation)));
    }

    public Vector3 getScale() {
        return scale;
    }

    public void setScale(Vector3 scale) {
        this.scale = scale;
    }
}
