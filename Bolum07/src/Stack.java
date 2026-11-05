/*
 * Yigin (stack) soyut veri tipi: ekleme ve cikarma yalnizca ustten yapilir (LIFO).
 * DiziYigin ve DinamikDiziYigin bu arayuzu gerceklestirir.
 *
 * Not: java.util.Stack ile ayni addadir; bu klasordeki dosyalar java.util.Stack
 * kullanmaz, kutuphane yigini gerektiginde Deque / ArrayDeque kullanilir.
 */
public interface Stack<E> {
    int size();          // oge sayisi
    boolean isEmpty();   // yigin bos mu?
    void push(E e);      // uste yeni oge ekler
    E top();             // ustteki ogeyi cikarmadan dondurur; bossa null
    E pop();             // ustteki ogeyi cikarir ve dondurur; bossa istisna
}
