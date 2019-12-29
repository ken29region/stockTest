package ken.stockTest.controller;

import ken.stockTest.entity.Product;
import ken.stockTest.repositories.RepositoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductPageController {

    @Autowired
    private RepositoryFacade globalRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public String getProductsTable(){
        return "products";
    }

    @GetMapping("/add")
    public String addProductForm(){
        return "addProduct";
    }

    @PostMapping("/add")
    public String addProduct(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "category") String categoryCandidate,
            @RequestParam(name = "price") String priceCandidate,
            @RequestParam("file")MultipartFile file
            ) throws IOException {

        Product product = Product.builder()
                .name(name)
                .description(description)
                .category(globalRepo.findCategoryByName(categoryCandidate))
                .build();


        if(file != null){

            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultPathFileName =  uuidFile + "-" + file.getOriginalFilename();

            System.out.println(resultPathFileName);

            File newFile = new File(uploadDir + "/" + resultPathFileName);

            System.out.println(newFile.getAbsolutePath());
            file.transferTo(newFile);

            product.setImageSrc(resultPathFileName);
        }

        globalRepo.addProduct(product);

        System.out.println(name);
        System.out.println(description);
        System.out.println(categoryCandidate);
        System.out.println(priceCandidate);
        System.out.println(file.getOriginalFilename());

        return "addProduct";
    }

}