package jpact5.dao;

import java.util.List;

import jpact5.entity.Video;

public interface IVideoDao {


	void insert (Video video);
	void update (Video video);
	void delete (int cateid) throws Exception;
	Video findById (int cateid);
	List<Video> findAll();
	List<Video> findByCategoryname (String catename);
	List<Video> findAll(int page, int pagesize);
	int count();
}
