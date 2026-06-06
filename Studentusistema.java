public class StudentuSistema {

    public static void main(String[] args) {
        String[] studenti = {
            "1,Janis Berzins,DP2-4",
            "2,Anna Kazoka,DP2-4",
            "3,Karina Liepina,DP2-4"
        };

        printCLITable(studenti);
    }

    public static void printCLITable(String[] csvRows) {
        printLine("-", 49);
        System.out.printf("| %-21s | %-21s |\n", "id", "students / grupa");
        printLine("-", 49);
        
        for (int i = 0; i < csvRows.length; i++) {
            String[] parts = csvRows[i].split(",");
            if (parts.length >= 2) {
                System.out.printf("| %-21s | %-21s |\n", parts[0], parts[1]);
            }
        }
        printLine("-", 49);
    }

    public static void printLine(String character, int times) {
        System.out.println(character.repeat(times));
    }
}