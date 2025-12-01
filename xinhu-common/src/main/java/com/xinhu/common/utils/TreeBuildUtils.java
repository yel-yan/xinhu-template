package com.xinhu.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.lang.tree.parser.NodeParser;
import com.xinhu.common.utils.reflect.ReflectUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 扩展 hutool TreeUtil 封装系统树构建
 *
 * @author Lion Li
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TreeBuildUtils extends TreeUtil {

    /**
     * 根据前端定制差异化字段
     */
    public static final TreeNodeConfig DEFAULT_CONFIG = TreeNodeConfig.DEFAULT_CONFIG.setNameKey("label");

    public static <T, K> List<Tree<K>> build(List<T> list, NodeParser<T, K> nodeParser) {
        if (CollUtil.isEmpty(list)) {
            return null;
        }
        K k = ReflectUtils.invokeGetter(list.get(0), "parentId");
        return TreeUtil.build(list, k, DEFAULT_CONFIG, nodeParser);
    }

    /**
     * 构建多根节点的树结构（支持多个顶级节点）
     *
     * @param list        原始数据列表
     * @param getId       获取节点 ID 的方法引用，例如：node -> node.getId()
     * @param getParentId 获取节点父级 ID 的方法引用，例如：node -> node.getParentId()
     * @param parser      树节点属性映射器，用于将原始节点 T 转为 Tree 节点
     * @param <T>         原始数据类型（如实体类、DTO 等）
     * @param <K>         节点 ID 类型（如 Long、String）
     * @return 构建完成的树形结构（可能包含多个顶级根节点）
     */
    public static <T, K> List<Tree<K>> buildMultiRoot(List<T> list, Function<T, K> getId, Function<T, K> getParentId, NodeParser<T, K> parser) {
        if (CollUtil.isEmpty(list)) {
            return CollUtil.newArrayList();
        }

        Set<K> rootParentIds = StreamUtils.toSet(list, getParentId);
        rootParentIds.removeAll(StreamUtils.toSet(list, getId));

        // 构建每一个根 parentId 下的树，并合并成最终结果列表
        return rootParentIds.stream()
            .flatMap(rootParentId -> TreeUtil.build(list, rootParentId, parser).stream())
            .collect(Collectors.toList());
    }

}
