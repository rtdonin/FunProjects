/*
Created by: Margaret Donin
Date created: 05/27/20
Date revised:
*/

package rtdonin.numberspiral;

import static java.lang.Integer.parseInt;
import java.util.Scanner;

public class SpiralNumber {
    public static void main(final String[] args) {
        final Scanner input = new Scanner(System.in);
        
        System.out.println("What number spiral would you like to print?");
        int n = parseInt(input.nextLine());
        int squareRoot = (int)(Math.floor(Math.sqrt(n)));
        
        int[][] spiral = new int[squareRoot + 1][squareRoot + 1];
        
        int start = (int) Math.floor(squareRoot / 2);
        int i = start;
        int j = start;
        
        int iInc = -1;
        int jInc = 1;
        
        int k = 1;
        
        int fib = 1;
        int preFib = 1;
        
        boolean iOrj = false; //true, change i. false change j
        boolean side1 = false; // there are two sides to a square, gotta do both.
        int numbersInLine = 1;
        j++;
                
        while(k <= n) {
            spiral[i][j] = k;
            k++;
            numbersInLine++;
            
            if(numbersInLine >= fib){
                if(side1){
                    fib++;

                    side1 = false;
                } else {
                    side1 = true;
                }
                
                numbersInLine = 0;
                iOrj = !iOrj;
                
                if (iOrj) {
                    iInc *= -1;
                } else {
                    jInc *= -1;
                }
            }
            
            if (iOrj) {
                i += iInc;
            } else {
                j += jInc;
            }
        }
        
        System.out.println("");
        
        for(int a = 0; a < squareRoot + 1; a++) {
            for (int b = 0; b < squareRoot + 1; b++){
                
                if(spiral[a][b] != 0 || a == start && b == start){
                    System.out.print(spiral[a][b]);
                } else {
                    System.out.print("*");
                }
                
                System.out.print(" ");
            }
            System.out.println("");
        }

    }
}
