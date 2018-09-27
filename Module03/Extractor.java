import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * @author  Tom Fenyak (tjf0027@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version June 09 2018
 *
 */
public class Extractor {
   
   /** raw data: all (x,y) points from source data. */
   private Point[] points;
   
   /** lines identified from raw data. */
   private SortedSet<Line> lines;
  
   /**
    * Builds an extractor based on the points in the file named by filename. 
    */
   public Extractor(String filename) throws IOException {
      Scanner scan = new Scanner(new File(filename));
      int size = scan.nextInt();
      points = new Point[size];
      int x;
      int y;
   
      for (int i = 0; i < size; i++) {
         x = scan.nextInt();
         y = scan.nextInt();
         points[i] = new Point(x, y);
      }
   }
  
   /**
    * Builds an extractor based on the points in the Collection named by pcoll. 
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.toArray(new Point[]{});
   }
  
   /**
    * Returns a sorted set of all line segments of exactly four collinear
    * points. Uses a brute-force combinatorial strategy. Returns an empty set
    * if there are no qualifying line segments.
    *
    * @return lines that have four collinear points
    */
   public SortedSet<Line> getLinesBrute() {
      lines = new TreeSet<Line>();
      
      for (int a = 0; a < points.length; a++) {
         for (int b = a + 1; b < points.length; b++) {
            for (int c = b + 1; c < points.length; c++) {
               for (int d = c + 1; d < points.length; d++) {
               
                  double slope1 = points[a].slopeTo(points[b]);
                  double slope2 = points[a].slopeTo(points[c]);
                  double slope3 = points[a].slopeTo(points[d]);
               
                  if (slope1 == slope2 && slope2 == slope3
                     && slope1 == slope3) {
                     Line bruteLine = new Line();
                     bruteLine.add(points[a]);
                     bruteLine.add(points[b]);
                     bruteLine.add(points[c]);
                     bruteLine.add(points[d]);
                  
                     lines.add(bruteLine);
                  }
               }
            }
         }
      }
      return lines;
   }
  
   /**
    * Returns a sorted set of all line segments of at least four collinear
    * points. The line segments are maximal; that is, no sub-segments are
    * identified separately. A sort-and-scan strategy is used. Returns an empty
    * set if there are no qualifying line segments.
    *
    * @return lines with 4 collinear points using sort-and-scan
    */
   public SortedSet<Line> getLinesFast() {
      lines = new TreeSet<Line>();
      Point[] sorted = Arrays.copyOf(points, points.length);
      Line fastLine = new Line();
      boolean canAdd = true;
      
      for (int a = 0; a < points.length; a++) {
         Arrays.sort(sorted, points[a].slopeOrder);
      
         for (int b = 0; b < points.length; b++) {
            fastLine.add(sorted[0]);
            canAdd = fastLine.add(sorted[b]);
         
            if (!canAdd) {
               if (fastLine.length() >= 4) {
                  lines.add(fastLine);
               }
            
               fastLine = new Line();
               fastLine.add(sorted[b]);
            }
         }
      }
      
      return lines;
   }
   
}
