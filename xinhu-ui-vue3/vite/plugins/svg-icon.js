/*
 * @Description: 
 * @Version: 1.0
 * @Autor: yel
 * @Date: 2025-11-26 16:23:13
 * @LastEditors: yel
 * @LastEditTime: 2025-11-26 16:32:59
 */
import { createSvgIconsPlugin } from 'vite-plugin-svg-icons'
import path from 'path'

export default function createSvgIcon(isBuild) {
    return createSvgIconsPlugin({
		iconDirs: [path.resolve(process.cwd(), 'src/assets/icons/svg')],
        symbolId: 'icon-[dir]-[name]',
        svgoOptions: isBuild
    })
}
