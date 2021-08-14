package LPTFOURVTWO;

import java.io.*;
import java.util.*;
 
public class dpagain{
    public static double memo[][] = new double[3005][3005];
    public static double p[] = new double[3005];
 
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        memo[0][0] = 1;
        for (int i = 1; i <= N; i++){
            p[i] = sc.nextDouble();
        }
 
        for (int i = 1; i <= N; i++){
            for (int j = 0; j <= i; j++){
                memo[i][j] = (1 - p[i]) * memo[i-1][j];
                if (j > 0){
                    memo[i][j] += p[i] * memo[i-1][j-1];
                }
            }
        }
        double answer = 0;
        for (int j = N/2+1; j <= N; j++){ 
            answer += memo[N][j];
        }
        System.out.println(answer); 
    }
}