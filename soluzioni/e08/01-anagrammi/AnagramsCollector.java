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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class AnagramsCollector implements Iterable<WordList> {

  private final Map<WordSignature, WordList> signature2anagrams = new HashMap<>();

  public void add(final String word) {
    final WordSignature signature = new WordSignature(word);
    WordList anagrams = signature2anagrams.get(signature);
    if (anagrams == null) {
      anagrams = new WordList();
      signature2anagrams.put(signature, anagrams);
    }
    anagrams.add(word);
  }

  @Override
  public Iterator<WordList> iterator() {
    final List<WordList> lisfOfAnagrams = new ArrayList<>(signature2anagrams.values());
    lisfOfAnagrams.sort(null);
    final Iterator<WordList> it = lisfOfAnagrams.iterator();
    return new Iterator<>() {

      private WordList next = null;

      @Override
      public boolean hasNext() {
        if (next != null) return true;
        if (!it.hasNext()) return false;
        next = it.next();
        return next.size() > 1;
      }

      @Override
      public WordList next() {
        if (!hasNext()) throw new NoSuchElementException();
        final WordList res = next;
        next = null;
        return res;
      }
    };
  }
}
