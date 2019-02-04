package RenderEngineBase;

public class Face {
    private int[] vertexIndexes;
    private String color;

    public Face(int[] triangleIndexes, String color) {
        this.vertexIndexes = triangleIndexes;
        this.color = color;
    }
    public int[] getVertexIndexes() {
        return vertexIndexes;
    }

    public String getColor() {
        return color;
    }
}
