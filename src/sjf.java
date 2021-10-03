import java.util.Scanner;

public class sjf {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresar número de procesos: ");
        int n = sc.nextInt();
        int pid[] = new int[n];// ID del proceso
        int at[] = new int[n];// tiempo de llegada
        int bt[] = new int[n];// tiempo de comienzo
        int ct[] = new int[n];// tiempo de finalización
        int ta[] = new int[n];// tiempo de respuesta
        int wt[] = new int[n];// tiempo de espera
        int f[] = new int[n];// para marcar que un proceso está completo

        int st = 0, tot = 0;
        float avgwt = 0/* Promedio de espera */, avgta = 0/* Promedio de tiempo de respuesta */;

        for (int i = 0; i < n; i++) {
            System.out.println("Ingrese el proceso " + (i + 1) + ": Tiempo de llegada");
            at[i] = sc.nextInt();
            System.out.println("Ingrese el proceso " + (i + 1) + ": Tiempo de inicio");
            bt[i] = sc.nextInt();
            pid[i] = i + 1;
            f[i] = 0;
        }

        while (true) {
            int c = n, min = 999;
            if (tot == n)
                break;
            for (int i = 0; i < n; i++) {
                if ((at[i] <= st) && (f[i] == 0) && (bt[i] < min)) {
                    min = bt[i];
                    c = i;
                }
            }
            if (c == n)
                st++;
            else {
                ct[c] = st + bt[c];
                st += bt[c];
                ta[c] = ct[c] - at[c];
                wt[c] = ta[c] - bt[c];
                f[c] = 1;
                pid[tot] = c + 1;
                tot++;
            }
        }
        System.out.println("\npid   llegada comienzo  completado turno espera");
        for (int i = 0; i < n; i++) {
            avgwt += wt[i];
            avgta += ta[i];
            System.out.println(pid[i] + "\t\t" + at[i] + "\t\t" + bt[i] + "\t\t" + ct[i] + "\t\t\t" + ta[i] + "\t\t" + wt[i]);
        }
        System.out.println("\nPromedio de tiempo de respuesta: " + (float) (avgta / n));
        System.out.println("\nPromedio de tiempo de espera: " + (float) (avgwt / n));
        sc.close();
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + " ");
        }
    }
}
