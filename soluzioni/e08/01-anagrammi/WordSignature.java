/*

Copyright 2021 Luca Prigioniero, Massimo Santini

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

import java.util.Arrays;

public class WordSignature {

  private final String signature;

  public WordSignature(final String word) {
    char[] chars = word.toCharArray();
    Arrays.sort(chars);
    this.signature = new String(chars);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof WordSignature)) return false;
    return signature.equals(((WordSignature) obj).signature);
  }

  @Override
  public int hashCode() {
    return signature.hashCode();
  }

  @Override
  public String toString() {
    return signature;
  }
}
