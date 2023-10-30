
public class ElmaAgaci {
	
	private String meyve;
	private ElmaAgaci solDal;
	private ElmaAgaci sagDal;

	public ElmaAgaci(String meyve) {
		this.meyve = meyve;
		this.solDal = null;
		this.sagDal = null;
	}

	public void solDalaEkle(ElmaAgaci dal) {
		this.solDal = dal;
	}

	public void sagDalaEkle(ElmaAgaci dal) {
		this.sagDal = dal;
	}

	public String meyveGetir() {
		return this.meyve;
	}

	public ElmaAgaci solDalGetir() {
		return this.solDal;
	}

	public ElmaAgaci sagDalGetir() {
		return this.sagDal;
	}

	public static void main(String[] args) {
		
		ElmaAgaci agac = new ElmaAgaci("Ana Elma");
		ElmaAgaci solDal = new ElmaAgaci("Sol Elma");
		ElmaAgaci sagDal = new ElmaAgaci("Sağ Elma");
		ElmaAgaci solSolDal = new ElmaAgaci("Sol Sol Elma");
		ElmaAgaci solSagDal = new ElmaAgaci("Sol Sağ Elma");

		agac.solDalaEkle(solDal);
		agac.sagDalaEkle(sagDal);
		solDal.solDalaEkle(solSolDal);
		solDal.sagDalaEkle(solSagDal);

		System.out.println("Ana dalın meyvesi: " + agac.meyveGetir());
		System.out.println("Sol dalın meyvesi: " + agac.solDalGetir().meyveGetir());
		System.out.println("Sağ dalın meyvesi: " + agac.sagDalGetir().meyveGetir());
		System.out.println("Sol Sol dalın meyvesi: " + agac.solDalGetir().solDalGetir().meyveGetir());
		System.out.println("Sol Sağ dalın meyvesi: " + agac.solDalGetir().sagDalGetir().meyveGetir());
	}
}

