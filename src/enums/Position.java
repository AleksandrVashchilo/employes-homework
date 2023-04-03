import java.util.Random;

public enum Position {

    ASSISTANT("Assistant"),
    CLERK("Clerk"),
    DEVELOPER("Developer"),
    DRIVER("Driver"),
    ENGINEER("Engineer"),
    MANAGER("Manager");

    private final String position;

    Position(String position) {
        this.position = position;
    }

   public static Position getRandomPosition() {
        Position[] positions = Position.values();
        Random random = new Random();
        return positions[random.nextInt(values().length)];
   }


    @Override
    public String toString() {
        return position;
    }
}
