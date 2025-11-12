package phoneBookTask2;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private String lastName;

    @Override
    public String toString() {
        return name + " " + lastName;
    }
}