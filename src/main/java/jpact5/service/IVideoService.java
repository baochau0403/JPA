package jpact5.service;

import java.util.List;

import jpact5.entity.Video;

public interface IVideoService  {

	void insert(Video video);
	void update(Video video);
	void delete(int videoid) throws Exception;
	Video findById(int videoid);
	List<Video> findAll();
	List<Video> findByVideoname(String videoname);
	List<Video> findAll(int page,int pagesize);
	int count();

}
