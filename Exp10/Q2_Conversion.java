import java.util.Scanner;

class InvalidTemperatureException extends RuntimeException {
    private double invalidValue;

    public InvalidTemperatureException() {
        super("Temperature cannot be below absolute zero.");
        this.invalidValue = 0.0;
    }

    public InvalidTemperatureException(double invalidValue) {
        super("Temperature cannot be below absolute zero: " + invalidValue);
        this.invalidValue = invalidValue;
    }

    @Override
    public String toString() {
        return "InvalidTemperatureException Object - Invalid Value Provided: " + this.invalidValue;
    }
}

class TemperatureConverter {
    private String converterName;

    public TemperatureConverter() {
        this.converterName = "DefaultConverter";
    }

    public TemperatureConverter(String converterName) {
        this.converterName = converterName;
    }

    public void celsiusToFahrenheit(String input) {
        double celsius = Double.parseDouble(input);
        if (celsius < -273.15) {
            throw new InvalidTemperatureException(celsius);
        }
        double fahrenheit = (celsius * 9 / 5) + 32;
        System.out.println("Result in Fahrenheit: " + fahrenheit);
    }

    @Override
    public String toString() {
        return "TemperatureConverter Object - Name: " + this.converterName;
    }
}

public class Q2_Conversion {
    private String runId;

    public Q2_Conversion() {
        this.runId = "DefaultRun";
    }

    public Q2_Conversion(String runId) {
        this.runId = runId;
    }

    @Override
    public String toString() {
        return "Q2_Conversion Object - Run ID: " + this.runId;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Run ID to create main object: ");
        String runId = scanner.nextLine();
        Q2_Conversion mainObject = new Q2_Conversion(runId);
        System.out.println(mainObject.toString());

        System.out.print("Enter name to create TemperatureConverter object: ");
        String name = scanner.nextLine();
        TemperatureConverter converter = new TemperatureConverter(name);
        System.out.println(converter.toString());
        System.out.println();

        System.out.print("Enter a valid temperature in Celsius: ");
        String validTemp = scanner.nextLine();
        try {
            converter.celsiusToFahrenheit(validTemp);
        } catch (InvalidTemperatureException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format.");
        } finally {
            System.out.println("Conversion attempt done\n");
        }

        System.out.print("Enter a non-numeric string (e.g., hot): ");
        String invalidString = scanner.nextLine();
        try {
            converter.celsiusToFahrenheit(invalidString);
        } catch (InvalidTemperatureException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format.");
        } finally {
            System.out.println("Conversion attempt done\n");
        }

        System.out.print("Enter a temperature below absolute zero (e.g., -300): ");
        String absoluteZeroTemp = scanner.nextLine();
        try {
            converter.celsiusToFahrenheit(absoluteZeroTemp);
        } catch (InvalidTemperatureException e) {
            System.out.println(e.getMessage());
            System.out.println(e.toString());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format.");
        } finally {
            System.out.println("Conversion attempt done");
        }

        scanner.close();
    }
}