import hydraulic.*;

public class prova {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HSystemExt s = new HSystemExt();
		Source src = new Source("Src");
		Tap t = new Tap("Tap");
		s.addElement(src);
		s.addElement(t);
		
		src.connect(t);
		
		String layout = s.layout();
		System.out.println(layout);

	}

}
