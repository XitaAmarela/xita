package Xita;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ImagesView {

	private List<String> images;
	
	@PostConstruct
    public void init(){
    	images = new ArrayList<String>();    	
    	for (int i = 1; i < 8; i++) {
			images.add("galeria"+i+".jpg");
		}
    }
	public List<String> getImages(){
		return images;
	}
}
