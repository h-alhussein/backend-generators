package com.dev.generators.qr;

    import com.google.zxing.BarcodeFormat;
    import com.google.zxing.client.j2se.MatrixToImageWriter;
    import com.google.zxing.common.BitMatrix;
    import com.google.zxing.qrcode.QRCodeWriter;
    import lombok.RequiredArgsConstructor;
    import org.springframework.context.annotation.Bean;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.http.converter.BufferedImageHttpMessageConverter;
    import org.springframework.http.converter.HttpMessageConverter;
    import org.springframework.web.bind.annotation.*;
    import javax.imageio.ImageIO;
    import java.awt.image.BufferedImage;
    import java.io.ByteArrayOutputStream;
    import java.util.Base64;
    import java.util.Map;
    import java.util.Objects;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class QRGeneratorController {



    @PostMapping(value = "/generate-code", produces = "application/json")
    public ResponseEntity<?> generateCode(@RequestBody Map<String, String> requestData) throws Exception {
        String website = requestData.get("website");
        if (Objects.equals(website, "")) return new ResponseEntity<>("Der Text darf nicht leer sein", HttpStatus.BAD_REQUEST);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(website, BarcodeFormat.QR_CODE, 300, 300);
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "png", baos);
        byte[] imageBytes = baos.toByteArray();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);



return new ResponseEntity<>(base64Image,HttpStatus.OK);




    }

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

}

