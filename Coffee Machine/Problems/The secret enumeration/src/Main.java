public class Main {

    public static void main(String[] args) {
        Secret[] secrets = Secret.values();
        int starStartingWodrs=0;

        for (Secret word: secrets
             ) { if(word.toString().substring(0,4).equals("STAR")){
                starStartingWodrs++;
        }
        }
        System.out.println(starStartingWodrs);
    }
}

/* At least two constants start with STAR
 */
enum Secret {
    STAR, CRASH, START, // ...
}
