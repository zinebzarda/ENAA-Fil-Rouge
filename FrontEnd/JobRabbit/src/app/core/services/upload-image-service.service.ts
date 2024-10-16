import { Injectable } from '@angular/core';
import axios from "axios";

@Injectable({
  providedIn: 'root'
})
export class UploadImageServiceService {

  uploadImageToCloudinary(file: string): Promise<string> {
    const cloudinaryUrl = 'https://api.cloudinary.com/v1_1/dlppknslh/upload';
    const unsignedUploadPreset = 'taskRabbit';
    const formData = new FormData();
    formData.append('file', file);
    formData.append('upload_preset', unsignedUploadPreset);

    return axios.post(cloudinaryUrl, formData)
      .then(response => {
        if (response.data.secure_url) {
          return response.data.secure_url;
        } else {
          throw new Error('Upload failed');
        }
      })
      .catch(error => {
        throw error;
      });
  }

}
