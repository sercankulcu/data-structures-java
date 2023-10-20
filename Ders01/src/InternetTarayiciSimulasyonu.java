class WebPage {
    String url;
    WebPage left;
    WebPage right;

    public WebPage(String url) {
        this.url = url;
        this.left = null;
        this.right = null;
    }
}

public class InternetTarayiciSimulasyonu {
    private WebPage root;

    public void ziyaretEkle(String url) {
        root = ekle(root, url);
    }

    private WebPage ekle(WebPage currentPage, String url) {
        if (currentPage == null) {
            return new WebPage(url);
        }

        int comparison = url.compareTo(currentPage.url);

        if (comparison < 0) {
            currentPage.left = ekle(currentPage.left, url);
        } else if (comparison > 0) {
            currentPage.right = ekle(currentPage.right, url);
        }

        return currentPage;
    }

    public void gezinmeGecmisiGoster() {
        System.out.println("Internet Tarayıcı Gezinme Geçmişi (Alfabetik Sıra):");
        gezinmeGecmisiYazdir(root);
    }

    private void gezinmeGecmisiYazdir(WebPage currentPage) {
        if (currentPage != null) {
            gezinmeGecmisiYazdir(currentPage.left);
            System.out.println(currentPage.url);
            gezinmeGecmisiYazdir(currentPage.right);
        }
    }

    public static void main(String[] args) {
        InternetTarayiciSimulasyonu tarayici = new InternetTarayiciSimulasyonu();
        tarayici.ziyaretEkle("www.google.com");
        tarayici.ziyaretEkle("www.openai.org");
        tarayici.ziyaretEkle("www.yahoo.com");
        tarayici.ziyaretEkle("www.apple.com");
        tarayici.ziyaretEkle("www.facebook.com");

        tarayici.gezinmeGecmisiGoster();
    }
}
