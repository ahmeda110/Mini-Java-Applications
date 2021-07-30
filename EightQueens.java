/**
@author     Ahmed Abbas <a href="mailto:ahmed.abbas1@ucalgary.ca">ahmed.abbas1@ucalgary.ca</a>
@version    1.1
@since      1.0
*/

/**
 * This class places queens and empty spaces on a chess board manually as well
 * as place a set number of queens <8 on a board strategically.
 */
class EightQueens implements Cloneable {

  private char[][] board = new char[8][8]; // chess-board
  private int N = 8;

  /**
   * Deep copy method
   */
  public Object clone() throws CloneNotSupportedException {
    char[][] c2 = new char[8][8];
    EightQueens c1 = (EightQueens) super.clone();

    for (int a = 0; board.length > a; a++) {
      for (int b = 0; board.length > b; b++) {
        c2[a][b] = board[a][b];
      }
    }
    c1.board = c2;
    return c1;
  }

  /**
   * Constructor - Populates board
   */
  public EightQueens() {
    for (int a = 0; a < board.length; a++) {
      for (int b = 0; b < board[a].length; b++) {
        board[a][b] = 'o';
      }
    }
  }

  /**
   * Getter - gets current state of board
   * 
   * @return board
   */
  public char[][] getBoard() {
    return this.board;
  }

  /**
   * Setter - places queen in cell given
   * 
   * @param row    to place
   * @param column to place
   */
  public void setQueen(int row, int column) {
    board[row][column] = 'Q';
  }

  /**
   * Setter - places empty square(o) in cell given
   * 
   * @param row    to place
   * @param column to place
   */
  public void emptySquare(int row, int column) {
    board[row][column] = 'o';
  }

  /**
   * Places a given number of queens that no 2 queens hit each oter
   * 
   * @param queensRemaining to place
   * @return if can place or not
   */
  public boolean setQueens(int queensRemaining) {

    if (queensRemaining < 0 || queensRemaining > 8) {
      throw new IllegalArgumentException("Enter a valid number of queens (1-8)");
    }

    if (queensRemaining == 0) {
      return true;
    }

    for (int a = 0; N > a; a++) {
      for (int b = 0; N > b; b++) {
        if (!checkCell2(a, b) && board[a][b] == 'Q') {
          return false;
        } else if (checkCell(a, b) && board[a][b] != 'Q') {
          board[a][b] = 'Q';
          if (setQueens(queensRemaining - 1) == true) {
            return true;
          }
          board[a][b] = 'o';
        }
      }
    }
    return false;
  }

  /**
   * check if cell marked 'o' is under attack
   *
   * @param a cell to check row wise
   * @param b cell to check column wise
   * @return if can place
   */
  public boolean checkCell(int a, int b) {
    for (int c = 0; N > c; c++) {
      if (board[a][c] == 'Q' || board[c][b] == 'Q') {
        return false;
      }
    }

    for (int d = 0; N > d; d++) {
      for (int e = 0; N > e; e++) {
        if ((d + e) == (a + b) || (d - e) == (a - b)) {
          if (board[d][e] == 'Q')
            return false;
        }
      }
    }
    return true;
  }

  /**
   * check if cell marked 'o' is under attack
   *
   * @param a cell to check row wise
   * @param b cell to check column wise
   * @return if can place
   */
  public boolean checkCell2(int a, int b) {
    for (int c = 0; N > c; c++) {
      if ((board[a][c] == 'Q' || board[c][b] == 'Q') && b != c && a != c) {
        return false;
      }
    }

    for (int d = 0; N > d; d++) {
      for (int e = 1; N > e; e++) {
        if (((d + e) == (a + b) || (d - e) == (a - b)) && a != d && b != e) {
          if (board[d][e] == 'Q')
            return false;
        }
      }
    }
    return true;
  }

}
