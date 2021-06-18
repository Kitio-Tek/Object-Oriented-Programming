package sports;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private String name;
	private List<String> activity;

	public Category(String name, List<String> list) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.activity=new ArrayList<>();
		activity.addAll(list);
	}

	public String getName() {
		return name;
	}

	public List<String> getActivity() {
		return activity;
	}

}
