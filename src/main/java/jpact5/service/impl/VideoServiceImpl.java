package jpact5.service.impl;

import java.util.List;


import jpact5.dao.IVideoDao;
import jpact5.dao.impl.VideoDaoImpl;
import jpact5.entity.Video;
import jpact5.service.IVideoService;

public class VideoServiceImpl implements IVideoService {
	IVideoDao videoDao =new VideoDaoImpl();
	@Override
	public void insert(Video video) {
		videoDao.insert(video);
		
	}

	@Override
	public void update(Video video) {
		videoDao.update(video);
		
	}

	@Override
	public void delete(int videoid) throws Exception {
		videoDao.delete(videoid);		
	}

	@Override
	public Video findById(int videoid) {
		return videoDao.findById(videoid);
	}

	@Override
	public List<Video> findAll() {
		return videoDao.findAll();
	}

	@Override
	public List<Video> findByVideoname(String videoname) {
		return videoDao.findByVideoname(videoname);
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		return videoDao.findAll(page, pagesize);
	}

	@Override
	public int count() {
		return videoDao.count();
	}

}
