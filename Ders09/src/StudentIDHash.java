
public class StudentIDHash {

	/**
	 * Hash fonksiyonunu hesaplar.
	 * @param studentId Okul numarası (ab070c0de formatında, String olarak)
	 * @return Hash değeri (0–999 arasında bir integer)
	 */
	public static int calculateHash(String studentId) {
		// Okul numarasını doğrula
		if (studentId == null || studentId.length() != 9) {
			throw new IllegalArgumentException("Geçersiz okul numarası formatı");
		}

		// Parçaları çıkar
		int ab = Integer.parseInt(studentId.substring(0, 2)); // ab: Kayıt yılı
		int c = Integer.parseInt(studentId.substring(5, 6));  // c: Bölüm
		int de = Integer.parseInt(studentId.substring(7, 9)); // de: Kayıt sırası

		// Yıl, bölüm ve kayıt sırası kontrolü
		if (ab < 20 || ab > 24 || (c != 6 && c != 7) || de < 1 || de > 100) {
			throw new IllegalArgumentException("Geçersiz okul numarası değerleri");
		}

		// Hash fonksiyonunu uygula
		int yearFactor = (ab - 20) * 100;
		int departmentFactor = (c - 6) * 500;
		int hashValue = (yearFactor + departmentFactor + de) % 1000;

		return hashValue;
	}

	public static void main(String[] args) {
		// Test verileri
		String student1 = "210706003"; // Örnek okul numarası
		String student2 = "240707050";

		// Hash fonksiyonunu çağır ve sonuçları yazdır
		System.out.println("Hash değeri (student1): " + calculateHash(student1)); // Çıktı: 103
		System.out.println("Hash değeri (student2): " + calculateHash(student2)); // Çıktı: 950
	}
}
