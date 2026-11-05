# Data Structures in Java

Java code examples for the undergraduate **Data Structures (Veri Yapıları)** course at Giresun University. Each `BolumNN/src` folder holds chapter NN of the course and contains the code shown on that chapter's slides as small runnable programs. Class, method and variable names follow the slides, so each program's output can be compared directly with the slide examples.

Course page and slides: <https://sercankulcu.github.io/teaching/data-structures>

## Chapters

| Folder | Chapter | Programs |
|---|---|---|
| `Bolum01` | Introduction | References vs. values, generics (`Kutu`), the `Yigin` ADT and `DiziYigin` |
| `Bolum02` | Algorithm Analysis | Complexity examples, linear and binary search, doubling experiment, prefix averages |
| `Bolum03` | Arrays | Copying and comparing arrays, common array errors, mean/median/mode, Caesar cipher, multidimensional and jagged arrays, linear search, matching elements, insertion/deletion, selection/insertion/bubble sort, reversal, transpose, tic-tac-toe |
| `Bolum04` | Recursion | Factorial, recursive and iterative Fibonacci |
| `Bolum05` | Linked Lists | Singly, doubly and circular linked lists, sentinel doubly linked list, Floyd cycle detection, linked structure built from a matrix |
| `Bolum06` | Lists | `List` operations, `ArrayList`, `LinkedList`, `Vector`/`Stack`, iterators, removing while iterating, views and unmodifiable lists, `CopyOnWriteArrayList`, positional list, a list performance benchmark |
| `Bolum07` | Stacks | `Stack` interface; array, growable-array and linked stacks; string reversal, parenthesis check, next greater element, min-stack, Towers of Hanoi (recursive and with a task stack), stock span, infix → postfix/prefix, postfix evaluation |
| `Bolum08` | Queues and Deques | Linked queue, circular queue, deque, queue from two stacks, binary numbers, Josephus, sliding-window maximum, round robin |
| `Bolum09` | Trees | Array representation, recursive and stack-based traversals, level order, expression trees, rebuilding a tree from traversals, general trees |
| `Bolum10` | Search Trees | Binary search tree, successor/predecessor/range/validation queries, AVL tree, red-black tree, splay tree, a BST of student records |
| `Bolum11` | Priority Queues & Heaps | Unsorted/sorted array and linked-list priority queues, `MaxHeap` (up-heap, down-heap, bottom-up construction, key change), heap sort, `PriorityQueue`, k-th largest |
| `Bolum12` | Maps & Hash Tables | Hash functions, separate chaining with resizing, open addressing with deletion markers, linear/quadratic probing and double hashing, `Map` usage and `equals`/`hashCode`, two-sum, student-number hashing |
| `Bolum13` | Sets & Disjoint Sets | Set operations, hash set, sorted-array union/intersection, bit-vector sets, multiset and multimap, disjoint sets (quick-find, union by size, union by rank with path compression) |
| `Bolum14` | Graphs | Graph representations, BFS and DFS, connected components, bipartite test, topological sort and cycle detection, unweighted shortest paths, Dijkstra, Kruskal |

## Running the examples

Requires **JDK 17 or later** (some examples use records and `List.of`).

Every file is a standalone program with a `main` method in the default package, and no program waits for keyboard input. To compile and run one chapter:

```bash
cd Bolum07/src
javac -d ../bin *.java
java -cp ../bin ParantezKontrol
```

`Bolum06/src/performance` is in the `performance` package: compile it from `Bolum06/src` with `javac -d ../bin *.java performance/*.java` and run it as `java -cp ../bin performance.ListePerformansi`.

Comments and identifiers are in Turkish, written without Turkish characters so the files compile with any platform encoding.

## Contact

For questions or suggestions, contact [Sercan Külcü](https://github.com/sercankulcu) or open an issue on GitHub.
