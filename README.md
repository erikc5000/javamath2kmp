# JavaMath2KMP
 A Kotlin Multiplatform port of Java math functions not included in the standard library — primarily, checked arithmetic operations.

| Java | JavaMath2KMP Equivalent |
| --- | --- |
| `Math.floorDiv(x, y)` | `x floorDiv y` |
| `Math.floorMod(x, y)` | `x floorMod y` |
| `Math.addExact(x, y)` | `x plusExact y` |
| `Math.subtractExact(x, y)` | `x minusExact y` |
| `Math.multiplyExact(x, y)` | `x timesExact y` |
| `Math.negateExact(n)` | `n.negateExact()` |
| `Math.incrementExact(n)` | `n.incExact()` |
| `Math.decrementExact(n)` | `n.decExact()` |
| `Math.absExact(n)` | `absExact(n)` |
| `Math.toIntExact(n)` | `n.toIntExact()` |

Floor division and floor modulus were added to the standard library in 1.5, but the existing infix functions are preserved here with JVM implementations backed by java.Math.

#### Gradle Setup

```kotlin
implementation("dev.erikchristensen.javamath2kmp:javamath2kmp:0.4.7")
```
