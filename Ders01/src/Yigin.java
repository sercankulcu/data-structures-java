// Yigin ADT'sinin Java arayuzu (Slayt 18).
// Arayuz NE yapildigini soyler; NASIL yapildigi sinifa (DiziYigin) kalir.
public interface Yigin<E> {
	int size();         // oge sayisi
	boolean isEmpty();  // bos mu?
	void push(E e);     // uste ekle
	E top();            // usttekini dondur (cikarmadan)
	E pop();            // usttekini cikar ve dondur
}
