public class Rectangle {
    private int base;
    private int height;

    // Constructor con validación
    public Rectangle(int base, int height) {
        if (base < 0 || height < 0) {
            throw new IllegalArgumentException("Las dimensiones no pueden ser negativas");
        }
        this.base = base;
        this.height = height;
    }

    // Constructor copia
    public Rectangle(Rectangle r) {
        this(r.base, r.height);
    }

    // Getters
    public int getBase() {
        return base;
    }

    public int getHeight() {
        return height;
    }

    // Setters
    public void setBase(int base) {
        if (base < 0) {
            throw new IllegalArgumentException("La base no puede ser negativa");
        }
        this.base = base;
    }

    public void setHeight(int height) {
        if (height < 0) {
            throw new IllegalArgumentException("La altura no puede ser negativa");
        }
        this.height = height;
    }

    // Métodos funcionales
    public boolean isSquare() {
        return base == height;
    }

    public int area() {
        return base * height;
    }

    public int perimeter() {
        return 2 * (base + height);
    }

    public double diagonal() {
        return Math.sqrt(base * base + height * height);
    }

    public void turn() {
        int temp = base;
        base = height;
        height = temp;
    }

    public void putHorizontal() {
        if (height > base) {
            turn();
        }
    }

    public void putVertical() {
        if (base > height) {
            turn();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Rectangle)) return false;

        Rectangle other = (Rectangle) obj;

        // igualdad considerando rotación
        return (this.base == other.base && this.height == other.height)
                || (this.base == other.height && this.height == other.base);
    }

    @Override
    public int hashCode() {
        // usar suma y producto, independientes del orden
        return base * height + base + height;
    }

    @Override
    public String toString() {
        return "Rectangle[base=" + base + ", height=" + height + "]";
    }
}

