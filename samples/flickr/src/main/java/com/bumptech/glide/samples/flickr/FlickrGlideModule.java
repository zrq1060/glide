package com.bumptech.glide.samples.flickr;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.samples.flickr.api.Photo;
import java.io.InputStream;

/** Register {@link FlickrModelLoader} for the Flickr sample app. */
@GlideModule
public class FlickrGlideModule extends AppGlideModule {

  @Override
  public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
    super.applyOptions(context, builder);
    // 通过builder参数，可以配置一些Glide的参数，例如缓存大小、MemoryCache、DiskCache、各种Executor 等等，具体可以看GlideBuilder的方法
    builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888));
  }

  @Override
  public void registerComponents(
      @NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
    // 通过参数registry，可以注册 ModelLoader（数据加载） 、Encoder（写入数据）、Decoder（）
    registry.append(Photo.class, InputStream.class, new FlickrModelLoader.Factory());
  }

  // Disable manifest parsing to avoid adding similar modules twice.
  @Override
  public boolean isManifestParsingEnabled() {
    return false;
  }
}
