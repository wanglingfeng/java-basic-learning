package com.collaborativeFiltering.study;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.svd.ALSWRFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 使用mahout实现协同过滤
 *
 * Created by lfwang on 2017/5/9.
 */
public class MahoutTest {

    public final static int NEIGHBORHOOD_NUM = 2;
    public final static int RECOMMENDED_NUM = 3;

    public static void main(String... args) throws IOException, TasteException {
        RandomUtils.useTestSeed(); // 用来使得每次随机都一样,仅限用于测试

        DataModel model = new FileDataModel(new File("/Users/lfwang/GitProjects/java-basic-learning/src/main/java/com/collaborativeFiltering/study/item.csv"));

        userCF(model);
        itemCF(model);
        svd(model);
    }

    /**
     * 使用用户相似度矩阵，近邻算法。
     * 特性：容易实现，当用户数量不大时，计算很快。
     *
     * 结果说明：
     *　对于uid=1的用户，给他推荐计算得分最高的2个物品，104和106。
     *　对于uid=2的用户，给他推荐计算得分最高的1个物品，105。
     *　对于uid=3的用户，给他推荐计算得分最高的2个物品，102和103。
     *　对于uid=4的用户，给他推荐计算得分最高的1个物品，102。
     *　对于uid=5的用户，没有推荐。
     *
     * @param model
     * @throws TasteException
     */
    private static void userCF(DataModel model) throws TasteException {
        UserSimilarity userSimilarity = new EuclideanDistanceSimilarity(model);
        NearestNUserNeighborhood neighborhood = new NearestNUserNeighborhood(
                NEIGHBORHOOD_NUM, userSimilarity, model);
        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, userSimilarity);
        LongPrimitiveIterator iterator = model.getUserIDs();

        StringBuilder stringBuilder = new StringBuilder();

        while (iterator.hasNext()) {
            Long uid = iterator.nextLong();

            List<RecommendedItem> items = recommender.recommend(uid, RECOMMENDED_NUM);

            stringBuilder.append(String.format("uid: %s", uid));

            for (RecommendedItem item : items) {
                stringBuilder.append(String.format("(%s, %f)", item.getItemID(), item.getValue()));
            }

            stringBuilder.append("\n");
        }

        System.out.println("---- userCF ----");
        System.out.println(stringBuilder.toString());
    }

    /**
     * 物品相似度矩阵，近邻算法。
     * 特性：当用户数量不大时，计算很快，当构建一个新的物品相似时非常有用。
     *
     * 结果说明：
     *　对于uid=1的用户，给他推荐计算得分最高的3个物品，104、105和106。
     *　对于uid=2的用户，给他推荐计算得分最高的3个物品，105、106和107。
     *　对于uid=3的用户，给他推荐计算得分最高的3个物品，102、103和106。
     *　对于uid=4的用户，给他推荐计算得分最高的3个物品，102、105和107。
     *　对于uid=5的用户，给他推荐计算得分最高的1个物品，107。
     *
     * @param model
     * @throws TasteException
     */
    private static void itemCF(DataModel model) throws TasteException {
        ItemSimilarity itemSimilarity = new EuclideanDistanceSimilarity(model);
        Recommender recommender = new GenericItemBasedRecommender(model, itemSimilarity);

        LongPrimitiveIterator iterator = model.getUserIDs();

        StringBuilder stringBuilder = new StringBuilder();

        while (iterator.hasNext()) {
            Long uid = iterator.nextLong();

            List<RecommendedItem> items = recommender.recommend(uid, RECOMMENDED_NUM);

            stringBuilder.append(String.format("uid: %s", uid));

            for (RecommendedItem item : items) {
                stringBuilder.append(String.format("(%s, %f)", item.getItemID(), item.getValue()));
            }

            stringBuilder.append("\n");
        }

        System.out.println("---- itemCF ----");
        System.out.println(stringBuilder.toString());
    }

    /**
     * 维度数。
     * 结果比较好，会有大量预处理。
     *
     * 结果说明：
     *　对于uid=1的用户，给他推荐计算得分最高的3个物品，105、106和107。
     *　对于uid=2的用户，给他推荐计算得分最高的3个物品，105、106和107。
     *　对于uid=3的用户，给他推荐计算得分最高的3个物品，102、103和106。
     *　对于uid=4的用户，给他推荐计算得分最高的3个物品，102、105和107。
     *　对于uid=5的用户，给他推荐计算得分最高的1个物品，107。
     *
     * @param model
     * @throws TasteException
     */
    private static void svd(DataModel model) throws TasteException {
        Recommender recommender = new SVDRecommender(model, new ALSWRFactorizer(model, 10, 0.05, 10));

        LongPrimitiveIterator iterator = model.getUserIDs();

        StringBuilder stringBuilder = new StringBuilder();

        while (iterator.hasNext()) {
            Long uid = iterator.nextLong();

            List<RecommendedItem> items = recommender.recommend(uid, RECOMMENDED_NUM);

            stringBuilder.append(String.format("uid: %s", uid));

            for (RecommendedItem item : items) {
                stringBuilder.append(String.format("(%s, %f)", item.getItemID(), item.getValue()));
            }

            stringBuilder.append("\n");
        }

        System.out.println("---- svd ----");
        System.out.println(stringBuilder.toString());
    }
}
