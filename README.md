# Data Structures in Java

Java code examples for the undergraduate **Data Structures (Veri Yapıları)** course at Giresun University. Each `DersNN/src` folder belongs to one chapter of the course and contains the code shown on that chapter's slides as small runnable programs. Class, method and variable names follow the slides, so each program's output can be compared directly with the slide examples.

Course page and slides: <https://sercankulcu.github.io/teaching/data-structures>

## Chapters

| Folder | Chapter | Programs |
|---|---|---|
| `Ders01` | Introduction | References vs. values, generics (`Kutu`), the `Yigin` ADT and `DiziYigin`, complexity examples, linear and binary search, doubling experiment, prefix averages, recursion |
| `Ders02` | Arrays | Copying and comparing arrays, common array errors, mean/median/mode, Caesar cipher, multidimensional and jagged arrays, linear search, matching elements, insertion/deletion, selection/insertion/bubble sort, reversal, transpose, tic-tac-toe |
| `Ders03` | Linked Lists | Singly, doubly and circular linked lists, sentinel doubly linked list, Floyd cycle detection, linked structure built from a matrix |
| `Ders04` | Stacks | `Stack` interface; array, growable-array and linked stacks; string reversal, parenthesis check, next greater element, min-stack, Towers of Hanoi, stock span, infix → postfix/prefix, postfix evaluation |
| `Ders05` | Queues | Linked queue, circular queue, deque, simple priority queue, queue from two stacks, binary numbers, Josephus, sliding-window maximum, round robin |
| `Ders06` | Lists | `List` operations, `ArrayList`, `LinkedList`, `Vector`/`Stack`, iterators, removing while iterating, views and unmodifiable lists, `CopyOnWriteArrayList`, positional list, a list performance benchmark |
| `Ders07` | Trees | Array representation, recursive and stack-based traversals, level order, expression trees, rebuilding a tree from traversals, general trees |
| `Ders08` | Priority Queues & Heaps | Unsorted/sorted array and linked-list priority queues, `MaxHeap` (up-heap, down-heap, bottom-up construction, key change), heap sort, `PriorityQueue`, k-th largest |
| `Ders09` | Maps & Hash Tables | Hash functions, separate chaining with resizing, open addressing with deletion markers, linear/quadratic probing and double hashing, `Map` usage and `equals`/`hashCode`, two-sum, student-number hashing |
| `Ders10` | Search Trees | Binary search tree, successor/predecessor/range/validation queries, AVL tree, red-black tree, splay tree, a BST of student records |
| `Ders11` | Graphs | Graph representations, BFS and DFS, connected components, bipartite test, topological sort and cycle detection, unweighted shortest paths, Dijkstra |
| `Ders12` | Sets | Set operations, hash set, sorted-array union/intersection, bit-vector sets, multiset and multimap, disjoint sets (quick-find, union by size, union by rank with path compression), Kruskal |

## Running the examples

Requires **JDK 17 or later** (some examples use records and `List.of`).

Every file is a standalone program with a `main` method in the default package, and no program waits for keyboard input. To compile and run one chapter:

```bash
cd Ders04/src
javac -d ../bin *.java
java -cp ../bin ParantezKontrol
```

`Ders06/src/performance` is in the `performance` package: compile it from `Ders06/src` with `javac -d ../bin *.java performance/*.java` and run it as `java -cp ../bin performance.ListePerformansi`.

Comments and identifiers are in Turkish, written without Turkish characters so the files compile with any platform encoding.

## Contact

For questions or suggestions, contact [Sercan Külcü](https://github.com/sercankulcu) or open an issue on GitHub.
