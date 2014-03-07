package onionnpr;

import java.net.*;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Amdrwe
 */
public class OnionNPR {

    int streak = 0;
    int bestStreak = 0;
    Scanner input = new Scanner(System.in);
    Random r = new Random();
    ScrapedPageOnion theOnion = new ScrapedPageOnion(new URL("http://theonion.com/"));
    ScrapedFeedNPR npr = new ScrapedFeedNPR(new URL("http://www.npr.org/rss/rss.php?id=1001"));
    int selection;

    public static void main(String[] args) throws Exception {
        OnionNPR onionnpr = new OnionNPR();
    }

    public OnionNPR() throws Exception {

        this.printIntroText();
        selection = input.nextInt();
        switch (selection) {
            case 1:
                System.out.print("Choices are:\n1. The Onion\n2. NPR\n3. Quit\n");
                this.playGame();
                break;
            case 2:
                System.out.println("Maybe next time, then.");
                break;
            default:
                System.out.println("Please make a valid selection.");
        }

    }

    private void printIntroText() {
        System.out.println("The Onion, or NPR?");
        System.out.println("Menu:");
        System.out.println("1. Play");
        System.out.println("2. Quit");
        System.out.print(">");
    }

    private void playGame() {

        boolean isOnion;
        int randint = r.nextInt(100);
        if (randint < 50) {
            isOnion = true;
            theOnion.printRandomTitle();
        } else {
            isOnion = false;
            npr.printRandomTitle();
        }
        System.out.print(">");
        int s = input.nextInt();
        switch (s) {
            case 1:
                if (isOnion) {
                    System.out.println("Correct");
                    streak++;
                    System.out.format("Current Streak: %d\n\n", streak);
                    if (streak > bestStreak) {
                        bestStreak = streak;
                    }
                } else {
                    System.out.println("Wrong");
                    System.out.format("Streak ended at: %d\n\n", streak);
                    streak = 0;

                }
                playGame();
                break;
            case 2:
                if (!isOnion) {
                    System.out.println("Correct");
                    streak++;
                    System.out.format("Current Streak: %d\n\n", streak);
                    if (streak > bestStreak) {
                        bestStreak = streak;
                    }
                } else {
                    System.out.println("Wrong");
                    System.out.format("Streak ended at: %d\n\n", streak);
                    streak = 0;
                }
                playGame();
                break;
            case 3:
                System.out.println("Thanks for playing!");
                System.out.format("Best streak: %d\n", bestStreak);
                break;
            default:
                System.out.println("Invalid selection");
                playGame();
                break;
        }


    }
}
