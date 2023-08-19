package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);
        blog.getImageList().add(image);

        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        Image image = imageRepository2.findById(id).get();
        Blog blog = image.getBlog();
        blog.getImageList().remove(image);
        blogRepository2.save(blog);
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        String[] dimension1 = screenDimensions.split("X",-2);
        int A = Integer.parseInt(dimension1[0]);
        int B = Integer.parseInt(dimension1[1]);
        Image image = imageRepository2.findById(id).get();
        String[] dimension2 = image.getDimensions().split("X",-2);
        int X = Integer.parseInt(dimension2[0]);
        int Y = Integer.parseInt(dimension2[1]);
        int numOfImages = A/X * B/Y;
        return numOfImages;
    }
}
