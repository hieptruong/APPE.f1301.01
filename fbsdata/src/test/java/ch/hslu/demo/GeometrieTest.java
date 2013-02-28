/*
 * Copyright 2013 Roland Gisler
 * Hochschule Luzern Technik & Architektur, Switzerland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

/**
 * JUnit-Testfall fuer Klasse Geometrie.
 * @author Roland Gisler
 */
public class GeometrieTest {

   @Test
   public void testGetQuadrant1() {
      assertEquals("Punkt in Quadrant 1", 1, Geometrie.getQuadrant(1, 1));
   }

   @Test
   public void testGetQuadrant2() {
      assertEquals("Punkt in Quadrant 2", 2, Geometrie.getQuadrant(-1, 1));
   }

   @Test
   public void testGetQuadrant3() {
      assertEquals("Punkt in Quadrant 3", 3, Geometrie.getQuadrant(-1, -1));
   }

   @Test
   public void testGetQuadrant4() {
      assertEquals("Punkt in Quadrant 4", 4, Geometrie.getQuadrant(1, -1));
   }

   @Test
   @Ignore
   public void testGetQuadrantXAchse() {
      assertEquals("Punkt auf x-Achse", 0, Geometrie.getQuadrant(0, 1));
   }

   @Test
   @Ignore
   public void testGetQuadrantYAchse() {
      assertEquals("Punkt auf y-Achse", 0, Geometrie.getQuadrant(1, 0));
   }

   @Test
   @Ignore
   public void testGetQuadrantNullpunkt() {
      assertEquals("Punkt auf 0-Punkt", 0, Geometrie.getQuadrant(0, 0));
   }
}

