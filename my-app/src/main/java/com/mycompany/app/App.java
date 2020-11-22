package com.mycompany.app;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CreateBucketRequest;

import java.util.List;

public class App 
{
    private static AmazonS3 s3Client;
    public static void main( String[] args )
    {
        String bucketName = args[0];
        String region = args[1];
        s3Client=AmazonS3ClientBuilder.defaultClient();
        
        try {
                System.out.format("\nNew Bucket name '%s'...\n\n", bucketName);
                s3Client.createBucket(new CreateBucketRequest(bucketName, region));
            } catch (AmazonS3Exception e) {
                System.err.println(e.getErrorMessage());
            }
            ListMyBuckets();
    }
    
    private static void ListMyBuckets() {
        List<Bucket> buckets = s3Client.listBuckets();
        System.out.println("My buckets now are:");

        for (Bucket b : buckets) {
            System.out.println(b.getName());
        }
    }
}

