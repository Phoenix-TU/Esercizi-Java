/*

Copyright 2021 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

This is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This material is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this file.  If not, see <https://www.gnu.org/licenses/>.

*/

package it.unimi.di.prog2.temisvolti.algebretta;

import java.util.Objects;

public class MatriceDensa extends AbsMatrice {

  // SOF: rapcostr
  private final int[][] mat;

  private MatriceDensa(final int dim) {
    if (dim < 0) throw new IllegalArgumentException("La dimensione deve essere positiva.");
    mat = new int[dim][dim];
  }

  public MatriceDensa(final int[][] mat) {
    Objects.requireNonNull(mat);
    if (mat.length == 0) throw new IllegalArgumentException("La dimensione deve essere positiva.");
    final int dim = mat.length;
    this.mat = new int[dim][dim];
    for (int i = 0; i < dim; i++) {
      if (mat[i].length != dim) throw new IllegalArgumentException("L'array deve essere quadrato.");
      for (int j = 0; j < dim; j++) this.mat[i][j] = mat[i][j];
    }
  }

  // SOF: copy
  public MatriceDensa(final Matrice A) {
    this(Objects.requireNonNull(A).dim());
    for (int i = 0; i < dim(); i++) for (int j = 0; j < dim(); j++) mat[i][j] = A.val(i, j);
  }
  // EOF: copy
  // EOF: rapcostr

  // SOF: dimval
  @Override
  public int dim() {
    return mat.length;
  }

  @Override
  public int val(final int i, final int j) {
    requireValidIJ(i, j);
    return mat[i][j];
  }
  // EOF: dimval

  // SOF: peralpha
  @Override
  public Matrice per(final int alpha) {
    // SOF: perzero
    if (alpha == 0) return new MatriceNulla(dim());
    // EOF: perzero
    final MatriceDensa N = new MatriceDensa(dim());
    for (int i = 0; i < dim(); i++) for (int j = 0; j < dim(); j++) N.mat[i][j] = alpha * mat[i][j];
    return N;
  }
  // EOF: peralpha

  // SOF: piumat
  @Override
  public MatriceDensa pi??(final Matrice B) {
    Objects.requireNonNull(B);
    if (!conforme(B)) throw new IllegalArgumentException("Le matrici non sono conformi.");
    // SOF: piuzero
    if (B instanceof MatriceNulla) return this;
    // EOF: piuzero
    final MatriceDensa C = new MatriceDensa(dim());
    for (int i = 0; i < dim(); i++)
      for (int j = 0; j < dim(); j++) C.mat[i][j] = mat[i][j] + B.val(i, j);
    return C;
  }
  // EOF: piumat

  // SOF: permat
  @Override
  public Matrice per(final Matrice B) {
    Objects.requireNonNull(B);
    if (!conforme(B)) throw new IllegalArgumentException("Le matrici non sono conformi.");
    // SOF: perspeciale
    if (B instanceof MatriceNulla) return B;
    if (B instanceof MatriceIdentit??) return this;
    // EOF: perspeciale
    final MatriceDensa C = new MatriceDensa(dim());
    for (int i = 0; i < dim(); i++)
      for (int j = 0; j < dim(); j++)
        for (int k = 0; k < dim(); k++) C.mat[i][j] += mat[i][k] * B.val(k, j);
    return C;
  }
  // EOF: permat

  // SOF: pervec
  @Override
  public Vettore per(final Vettore v) {
    Objects.requireNonNull(v);
    if (!conforme(v))
      throw new IllegalArgumentException("Il vettore e la matrice non sono conformi.");
    // SOF: pervzero
    if (v instanceof VettoreNullo) return v;
    // EOF: pervzero
    final int[] temp = new int[mat.length];
    for (int i = 0; i < mat.length; i++)
      for (int j = 0; j < mat.length; j++) temp[i] += mat[i][j] * v.val(j);
    return new VettoreDenso(temp);
  }
  // EOF: pervec
}
