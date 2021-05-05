package diet;

public class Time implements Comparable<Time> {
private int h,m;

public Time(int k,int j){
	this.h=k;
	this.m=j;
}


@Override
public int compareTo(Time o) {
	return 60*h+m-(60*o.h+o.m);
}

@Override
public String toString() {
	return String.format("%02d:%02d", h,m);
}


}
