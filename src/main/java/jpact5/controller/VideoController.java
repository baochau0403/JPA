package jpact5.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jpact5.entity.Video;
import jpact5.service.IVideoService;
import jpact5.service.impl.VideoServiceImpl;

import static jpact5.util.Constant.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/add", "/admin/video/insert",
		"/admin/video/edit", "/admin/video/update", "/admin/video/delete", "/admin/video/search" })
public class VideoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public IVideoService videoService = new VideoServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("videos")) {
			List<Video> list = videoService.findAll();
			req.setAttribute("videos", list);
			req.getRequestDispatcher("/admin/video/video-list.jsp").forward(req, resp);
		} else if (url.contains("add")) {
			req.getRequestDispatcher("/admin/video/video-add.jsp").forward(req, resp);
		} else if (url.contains("edit")) {
			int id = Integer.parseInt(req.getParameter("id"));

			Video video = videoService.findById(id);
			req.setAttribute("cate", video);
			req.getRequestDispatcher("/admin/video/video-edit.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			String id = req.getParameter("id");
			try {
				videoService.delete(Integer.parseInt(id));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
		else if (url.contains("search"))
		{
			String search = req.getParameter("search");
			if (search !=null || search.strip().length()!=0)
			{
				List<Video> list = videoService.findByVideoname(search);
				req.setAttribute("listcate", list);
				req.getRequestDispatcher("/admin/video/video-list.jsp").forward(req, resp);
				return;
			}
			List<Video> list = videoService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/admin/video/video-list.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("insert")) {
			Video video = new Video();
			video.setTitle(req.getParameter("title"));
			video.setDescription(req.getParameter("description"));
			video.setUploaddate(new Date());
		
			String fileName = saveFile(req.getPart("file"));
			video.setVideoName(fileName);
			
			videoService.insert(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
			
		} else if (url.contains("update")) {

			int videoid = Integer.parseInt(req.getParameter("videoid"));
			Video video = videoService.findById(videoid);
			video.setTitle(req.getParameter("title"));
			video.setDescription(req.getParameter("description"));

			String fileName = saveFile(req.getPart("file"));
			if (fileName != null || fileName.isEmpty()) {
				video.setVideoName(fileName);
			}
			videoService.update(video);
			resp.sendRedirect(req.getContextPath() +"admin/videos");

		}
	}

	private String saveFile(Part filePart) throws IOException {
		if (filePart.getSize() <=0) {
			return null;
		}
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		String filePath = UPLOAD_DIRECTORY;
//		String filePath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
		File uploadDir = new File (filePath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		String newfileName = System.currentTimeMillis() + "_" + fileName;
		filePart.write(filePath + File.separator + newfileName);
		return newfileName;
		}
}
