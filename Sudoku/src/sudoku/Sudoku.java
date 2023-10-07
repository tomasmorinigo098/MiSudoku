
package sudoku;

import java.util.Random;

/**
 * 
 * @author Pablo Tomás Morinigo <tomasmorinigo098@gmail.com>
 */
public class Sudoku {
    
    private final int TAM_MATRIZ = 9;
    
    protected void vaciarSudoku(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = 0;
            }
        }
    }
    
    protected int[][] rellenarMatriz(int[][] matriz) { // lo distinto a este metodo me rellena la matriz
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {            
                int generado = (int) (Math.random() * 9 + 1);
                if (this.lugarDelSudoku(matriz, generado, i, j)) {
                    matriz[i][j] = generado;
                } else { 
                    if (this.analizadorSudoku(matriz));
                }  
            }
        }
        return matriz;
    }
    
    protected int[][] generarSudoku(int[][] matriz, int nivel) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {  
                int aux = j;
                int pos = new Random().nextInt(nivel + 1);
                j += pos;
                for (int k = aux; k < j && k < matriz.length; k++) {
                    matriz[i][k] = 0;
                }
            }
        }    
        return matriz;
    }
    
    protected boolean nroEnFila(int[][] sudoku, int num, int fila) {
        for (int i = 0; i < TAM_MATRIZ; i++) {
            if (sudoku[fila][i] == num) {
                return true;
            }
        }
        return false;
    }
    
    protected boolean nroEnColumna(int[][] sudoku, int num, int columna) {
        for (int i = 0; i < TAM_MATRIZ; i++) {
            if (sudoku[i][columna] == num) {
                return true;
            }
        }
        return false;
    }
    
    protected boolean nroEnCuadrante(int[][] sudoku, int num, int fila, int columna) {
        int localCuadranteFila = fila - fila % 3; //obtenemos el cuadrante fila 
        int localCuadranteColumna = columna - columna % 3; //obtenemos el cuadrante columna
        
        for (int i = localCuadranteFila; i < localCuadranteFila + 3; i++) {
            for (int f = localCuadranteColumna; f < localCuadranteColumna + 3; f++) {
                if (sudoku[i][f] == num) {
                    return true;
                }
            }
        }
        return false;
    }
    
    protected boolean lugarDelSudoku(int[][] sudoku, int num, int fila, int columna) {
        return !nroEnFila(sudoku, num, fila) && 
               !nroEnColumna(sudoku, num, columna) && 
               !nroEnCuadrante(sudoku, num, fila, columna);
    }
    
    /* recursividad */
    protected boolean analizadorSudoku(int[][] sudoku) {
        for (int fila = 0; fila < TAM_MATRIZ; fila++) {
            for (int columna = 0; columna < TAM_MATRIZ; columna++) {
                //pregunta si el sudoku recibido por parametro en la [fila][columna] es igual a 0
                if (sudoku[fila][columna] == 0) {
                    for (int nroAValidar = 1; nroAValidar <= TAM_MATRIZ; nroAValidar++) {
                        if (this.lugarDelSudoku(sudoku, nroAValidar, fila, columna)) {
                            sudoku[fila][columna] = nroAValidar;
                            if (this.analizadorSudoku(sudoku)) { //recursividad
                                return true;
                            } else {
                                sudoku[fila][columna] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    protected void muestraSudoku(int[][] sudo) {
        for (int fila = 0; fila < TAM_MATRIZ; fila++) {
            if (fila%3==0 && fila != 0) {
                System.out.println("----------------");
            }
            for (int columna = 0; columna < TAM_MATRIZ; columna++) {
                if (columna%3==0 && columna != 0) {
                    System.out.print("|");
                }
                System.out.print(sudo[fila][columna]);
                
            }
            System.out.println();
        }
    }
    
}
